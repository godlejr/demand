package com.demand.site.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.entity.File;
import com.demand.site.common.entity.News;
import com.demand.site.common.entity.NewsFile;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.FileFlag;
import com.demand.site.common.flag.UserFlag;
import com.demand.site.common.util.AwsS3Util;
import com.demand.site.repository.file.FileRepository;
import com.demand.site.repository.news.NewsRepository;
import com.demand.site.repository.newsfile.NewsFileRepository;
import com.demand.site.service.NewsService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private NewsFileRepository newsFileRepository;

	@Autowired
	private AwsS3Util awsS3Util;

	@Value("#{aws['aws.s3.domain']}")
	private String S3_DOMAIN_URL;

	@Value("#{aws['aws.s3.file.news.url']}")
	private String NEWS_FILE_URL;

	@Override
	public Page<News> getNewsByPageable(Pageable pageable) throws Exception {
		return newsRepository.findAllByPageable(pageable);
	}

	@Override
	public File saveNewsAvatar(MultipartFile multipartFile) throws Exception {
		String originalName = multipartFile.getOriginalFilename();
		String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
		long size = multipartFile.getSize();
		InputStream inputStream = multipartFile.getInputStream();

		String storageName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(NEWS_FILE_URL,
				inputStream, originalName);

		File file = new File();
		file.setExt(ext);
		file.setOriginalName(originalName);
		file.setStorageName(storageName);
		file.setSize((int) size);
		file.setType(FileFlag.NEWS_FILE_TYPE);

		return fileRepository.saveAndFlush(file);
	}

	@Override
	public void saveNews(User user, String title, String content, MultipartFile[] files, long avatarId)
			throws Exception {
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setUser(user);

		File avatarFile = fileRepository.findOne(avatarId);
		news.setAvatarFile(avatarFile);

		newsRepository.saveAndFlush(news);

		for (int i = 0; i < files.length; i++) {
			MultipartFile multipartFile = files[i];
			String originalName = multipartFile.getOriginalFilename();
			String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
			long size = multipartFile.getSize();
			InputStream inputStream = multipartFile.getInputStream();

			String storageName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(NEWS_FILE_URL,
					inputStream, originalName);

			File file = new File();
			file.setExt(ext);
			file.setOriginalName(originalName);
			file.setStorageName(storageName);
			file.setSize((int) size);
			file.setType(FileFlag.NEWS_FILE_TYPE);

			fileRepository.saveAndFlush(file);

			NewsFile newsFile = new NewsFile();
			newsFile.setFile(file);
			newsFile.setNews(news);
			newsFileRepository.saveAndFlush(newsFile);
		}

	}

	@Override
	public void deleteNews(User user, long id) throws Exception {
		News news = newsRepository.findOne(id);
		User newsUser = news.getUser();
		long newsUserId = newsUser.getId();

		long userId = user.getId();
		long userLevel = user.getLevel();

		if (newsUserId == userId || userLevel == UserFlag.ADMIN_LEVEL) {
			File avatarFile = news.getAvatarFile();
			List<NewsFile> newsFiles = news.getNewsFiles();

			for (NewsFile newsFile : newsFiles) {
				File file = newsFile.getFile();
				String storageName = file.getStorageName();
				fileRepository.delete(file);
				awsS3Util.deleteFileByLocationAndStorageFileName(NEWS_FILE_URL, storageName);
			}

			newsRepository.delete(news);
			fileRepository.delete(avatarFile);
		}
	}

	@Override
	public News getNewsById(long id) throws Exception {
		return newsRepository.findOne(id);
	}

	@Override
	public void deleteNewsAvatar(long id, long fileId) throws Exception {
		News news = newsRepository.findOne(id);
		File tempFile = new File();
		tempFile.setId(1);
		news.setAvatarFile(tempFile);

		File file = fileRepository.findOne(fileId);
		String storageName = file.getStorageName();

		newsRepository.saveAndFlush(news);
		fileRepository.delete(fileId);
		awsS3Util.deleteFileByLocationAndStorageFileName(NEWS_FILE_URL, storageName);
	}

	@Override
	public Map<String, News> getPrevPresentNextNewsMapsById(long id) throws Exception {
		Map<String, News> newsMap = new HashMap<String, News>();

		News news = newsRepository.findOne(id);

		newsMap.put("news", newsRepository.saveAndFlush(news));

		News prevNews = newsRepository.findFirstByIdLessThanOrderByIdDesc(id);
		News nextNews = newsRepository.findFirstByIdGreaterThanOrderByIdAsc(id);

		newsMap.put("prevNews", prevNews);
		newsMap.put("nextNews", nextNews);

		return newsMap;
	}

	@Override
	public void updateNews(long id, User user, String title, String content, MultipartFile[] files,
			String[] deletedFileStorageNames, long avatarId) throws Exception {
		News news = newsRepository.findOne(id);
		User newsUser = news.getUser();
		long newsUserId = newsUser.getId();

		long userId = user.getId();
		long userLevel = user.getLevel();

		if (newsUserId == userId || userLevel == UserFlag.ADMIN_LEVEL) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile multipartFile = files[i];
				String originalName = multipartFile.getOriginalFilename();
				String ext = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
				long size = multipartFile.getSize();
				InputStream inputStream = multipartFile.getInputStream();

				String storageName = awsS3Util.getStorageFileNameByUploadingFileAndFileNameToTheLocation(NEWS_FILE_URL,
						inputStream, originalName);

				File file = new File();
				file.setExt(ext);
				file.setOriginalName(originalName);
				file.setStorageName(storageName);
				file.setSize((int) size);
				file.setType(FileFlag.NEWS_FILE_TYPE);

				fileRepository.saveAndFlush(file);

				File avatarFile = fileRepository.findOne(avatarId);
				news.setTitle(title);
				news.setContent(content);
				news.setAvatarFile(avatarFile);

				newsRepository.saveAndFlush(news);

				NewsFile newsFile = new NewsFile();
				newsFile.setFile(file);
				newsFile.setNews(news);
				newsFileRepository.saveAndFlush(newsFile);
			}

			if (deletedFileStorageNames != null) {
				for (int i = 0; i < deletedFileStorageNames.length; i++) {
					String storageName = deletedFileStorageNames[i];
					fileRepository.deleteByStorageNameAndType(storageName, FileFlag.NEWS_FILE_TYPE);
					awsS3Util.deleteFileByLocationAndStorageFileName(NEWS_FILE_URL, storageName);
				}
			}
		}
	}

}
