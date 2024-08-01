package com.ourMenu.backend.domain.menulist.application;

import com.ourMenu.backend.domain.menu.dao.MenuRepository;
import com.ourMenu.backend.domain.menulist.dao.MenuListRepository;
import com.ourMenu.backend.domain.menulist.domain.MenuList;
import com.ourMenu.backend.domain.menulist.domain.MenuListStatus;
import com.ourMenu.backend.domain.menulist.dto.request.MenuListRequestDTO;
import com.ourMenu.backend.domain.menulist.dto.request.PatchMenuListRequest;
import com.ourMenu.backend.global.common.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MenuListService {

    private final MenuListRepository menuListRepository;
    private final MenuRepository menuRepository;

    /** 새 메뉴판 생성 */
//    @Transactional
//    public MenuList createMenuList(MenuList menuList) {
//        return menuListRepository.save(menuList);
//    }

    //메뉴판 생성
    @Transactional
    public MenuList createMenuList(MenuListRequestDTO request){
        MenuList menuList = MenuList.builder().title(request.getTitle()).imgUrl(request.getImg()).iconType(request.getIconType()).build();
        return menuListRepository.save(menuList);
    }


    // 메뉴판 조회 //
    @Transactional
    public MenuList getMenuListByName(String title) {
        return menuListRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("해당하는 메뉴판이 없습니다."));
    }

    //메뉴판 전체 조회
    @Transactional
    public List<MenuList> getAllMenuList(){
        return menuListRepository.findAll();
    }

    //메뉴판 업데이트
    @Transactional
    public MenuList updateMenuList(Long id, MenuListRequestDTO request) {
        MenuList menuList = menuListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuList not found"));

        MenuList.MenuListBuilder updateMenuListBuilder = menuList.toBuilder();


        if (request.getTitle() != null){
            updateMenuListBuilder.title(request.getTitle());
        }
        if (request.getImg() != null){
            updateMenuListBuilder.imgUrl(request.getImg());
        }

        if (request.getIconType() != null){
            updateMenuListBuilder.iconType(request.getIconType());
        }

        MenuList updateMenuList = updateMenuListBuilder.build();

        return menuListRepository.save(updateMenuList);
    }

    //메뉴판 삭제
    @Transactional
    public String removeMenuList(Long id){
        MenuList menuList = menuListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuList not found"));

        MenuList.MenuListBuilder removeMenuListBuilder = menuList.toBuilder();

        removeMenuListBuilder.status(Status.DELETED);

        MenuList removeMenuList = removeMenuListBuilder.build();

        return "OK";
    }

    /** 메뉴판 메뉴 추가 */
    /*


    @Transactional
    public MenuList deleteMenu(Long menuId, Long menuListId ) {

        MenuList menuList = menuListRepository.findById(menuListId)
                .orElseThrow(() -> new RuntimeException("해당하는 메뉴판이 없습니다."));


        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("해당하는 메뉴가 없습니다."));

        // 삭제할 메뉴와 관련된 중간테이블 찾기
        MenuMenuList menuMenuList = menuList.getMenuMenuLists().stream()
                .filter(m -> m.getMenu().getId().equals(menuId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 메뉴가 메뉴판에 없습니다."));

        // 메뉴판에서 삭제
        menuList.removeMenuMenuList(menuMenuList);

        return menuListRepository.save(menuList);
    }


    // 모든 메뉴판 조회
    @Transactional
    public List<MenuList> getAllMenuLists() {
        return menuListRepository.findAll();
    }

    // 특정 메뉴판 조회
    @Transactional
    public MenuList getMenuListById(Long id) {
        return menuListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 메뉴판이 없습니다."));
    }


    // 메뉴판 업데이트
    @Transactional
    public MenuList updateMenuList(Long id, MenuList menuListDetails) {
        MenuList menuList = menuListRepository.findById(id).orElse(null);
        if (menuList != null) {
            menuList.setTitle(menuListDetails.getTitle());
            menuList.setModifiedAt(menuListDetails.getModifiedAt());
            menuList.setStatus(menuListDetails.getStatus());
            menuList.setImgUrl(menuListDetails.getImgUrl());

            return menuListRepository.save(menuList);
        } else {
            return null;
        }
    }


    @Transactional
    public MenuList updateMenuList(Long id, PatchMenuListRequest patchMenuListRequest) {
        MenuList menuList = menuListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuList not found"));

        if (patchMenuListRequest.getTitle() != null){
            menuList.setTitle(patchMenuListRequest.getTitle());
        }
        if (patchMenuListRequest.getImgUrl() != null){
            menuList.setImgUrl(patchMenuListRequest.getImgUrl());
        }

        return menuListRepository.save(menuList);
    }

    // 메뉴판 삭제
    @Transactional
    public MenuList deleteMenuList(Long id) {
        MenuList menuList = menuListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당하는 메뉴판이 없습니다."));
        if (menuList != null) {
            menuList.setStatus(MenuListStatus.DELETED); // 상태를 'DELETED'로 변경
            return menuListRepository.save(menuList); //  상태를 저장
        } else {
            return null;
        }
    }

    */
}
