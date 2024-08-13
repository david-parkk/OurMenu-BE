package com.ourMenu.backend.domain.article.api.response;

import com.ourMenu.backend.domain.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArticleResponse {

    private Long articleId;
    private String userNickname;
    private String userImgUrl;
    private LocalDateTime createdBy;
    private String articleContent;
    private String articleThumbnail;
    private int articleViews;
    List<ArticleMenuResponse> articleMenus;

    public static ArticleResponse toDto(Article article) {
        return ArticleResponse.builder()
                .articleId(article.getId())
                .userNickname(article.getUser().getNickname())
                .userImgUrl(article.getUser().getImgUrl())
                .createdBy(article.getCreatedAt())
                .articleContent(article.getContent())
                .articleThumbnail(article.getArticleMenuList().isEmpty() ? null : article.getArticleMenuList().get(0).getMenuImage().getUrl())
                .articleViews(article.getViews())
                .articleMenus(article.getArticleMenuList().stream().map(ArticleMenuResponse::toDto).toList())
                .build();
    }
}
