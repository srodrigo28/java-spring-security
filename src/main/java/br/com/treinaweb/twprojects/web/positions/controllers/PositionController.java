package br.com.treinaweb.twprojects.web.positions.controllers;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twprojects.core.exceptions.PositionNotFoundException;
import br.com.treinaweb.twprojects.core.repositories.PositionRepository;
import br.com.treinaweb.twprojects.web.positions.dtos.PositionForm;
import br.com.treinaweb.twprojects.web.positions.mappers.PositionMapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/positions")
public class PositionController {

    private final PositionMapper positionMapper;
    private final PositionRepository positionRepository;

    @GetMapping
    public ModelAndView index() {
        var positions = positionRepository.findAll()
            .stream()
            .map(positionMapper::toPositionListItem)
            .toList();
        var model = Map.of("positions", positions);
        return new ModelAndView("positions/index", model);
    }

    @GetMapping("/create")
    public ModelAndView create() {
        var model = Map.of(
            "pageTitle", "Cadastrar cargo",
            "positionForm", new PositionForm()
        );
        return new ModelAndView("positions/form", model);
    }

    @PostMapping("/create")
    public String create(PositionForm positionForm) {
        var position = positionMapper.toPosition(positionForm);
        positionRepository.save(position);
        return "redirect:/positions";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var positionForm = positionRepository.findById(id)
            .map(positionMapper::toPositionForm)
            .orElseThrow(PositionNotFoundException::new);
        var model = Map.of(
            "pageTitle", "Editar cargo",
            "positionForm", positionForm
        );
        return new ModelAndView("positions/form", model);
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, PositionForm positionForm) {
        var postionToUpdate = positionRepository.findById(id)
            .orElseThrow(PositionNotFoundException::new);
        var positionData = positionMapper.toPosition(positionForm);
        BeanUtils.copyProperties(positionData, postionToUpdate, "id");
        positionRepository.save(postionToUpdate);
        return "redirect:/positions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        var postion = positionRepository.findById(id)
            .orElseThrow(PositionNotFoundException::new);
        positionRepository.delete(postion);
        return "redirect:/positions";
    }
    
}
