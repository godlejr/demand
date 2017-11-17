package com.demand.site.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.File;
import com.demand.site.common.entity.News;
import com.demand.site.common.entity.User;

public interface NewsService {

	Page<News> getNewsByPageable(Pageable pageable) throws Exception;

	File saveNewsAvatar(MultipartFile multipartFile) throws Exception;

	void saveNews(User user, String title, String content, MultipartFile[] files, long avatarId) throws Exception;

	void deleteNews(User user, long id) throws Exception;

	News getNewsById(long id) throws Exception;

	void deleteNewsAvatar(long id, long fileId) throws Exception;

	Map<String, News> getPrevPresentNextNewsMapsById(long id) throws Exception;

	void updateNews(long id, User user, String title, String content, MultipartFile[] files,
			String[] deletedFileStorageNames, long avatarId) throws Exception;
}
