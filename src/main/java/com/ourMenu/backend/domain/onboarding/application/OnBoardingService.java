package com.ourMenu.backend.domain.onboarding.application;

import com.ourMenu.backend.domain.menu.application.MenuService;
import com.ourMenu.backend.domain.menu.dao.MenuRepository;
import com.ourMenu.backend.domain.menu.domain.Menu;
import com.ourMenu.backend.domain.menu.dto.response.MenuDto;
import com.ourMenu.backend.domain.onboarding.domain.AnswerType;
import com.ourMenu.backend.domain.onboarding.domain.DefaultTag;
import com.ourMenu.backend.domain.onboarding.domain.Question;
import com.ourMenu.backend.domain.onboarding.util.S3Util;
import com.ourMenu.backend.domain.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OnBoardingService {

    private final MenuRepository menuRepository;
    private final MenuService menuService;

    public List<Question> getAllQuestion() {
        return Question.getAllQuestions();
    }

    @Transactional
    public List<Menu> findStoreByQuestionAnswer(Long userId, int questionId, AnswerType answerType) {
        List<String> foodStringList = Question.getAnswerFoodByIdAndAnswerType(questionId, answerType);
        Map<Long, Menu> map = new HashMap<>();
        for (String s : foodStringList) {
            List<Menu> menus = menuRepository.findMenusByTitleContainingAndUserId(s, userId);
            for (Menu menu : menus) {
                map.put(menu.getId(),menu);
            }
        }

        return map.values().stream().toList();
    }


    public List<Menu> findStoreByRandomTag(Long userId, DefaultTag randomTag) {
        return menuService.getAllMenusByTagName(randomTag.getTagName(), userId);
    }
}
