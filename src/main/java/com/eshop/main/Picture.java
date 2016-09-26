package com.eshop.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Picture {
	
	final private String productPicturePath = "C://Users//Sergey//Desktop//eShopImages//Images//";
	
	
	public void savePicture(FileUpload uploadForm, PictureType picType) 
											throws IllegalStateException, IOException {
		
		//list of images for product with current id
		List<File> existingPictures;
		
		String picturePattern;
		switch (picType) {
			case ARTICLE:
				picturePattern = PictureType.ARTICLE.getPictureType();
				existingPictures = getCertainImgNames(PictureType.ARTICLE);
				break;
			case BANNER:
				picturePattern = PictureType.BANNER.getPictureType();
				existingPictures = getCertainImgNames(PictureType.BANNER);
				break;
			default:
				//change this exception on more appropriate
				throw new IllegalStateException("Picture type has difined incorrect!");
		}
		savePictureOnDisk(uploadForm, picturePattern, existingPictures);
	}
	
	
	public void savePicture(FileUpload uploadForm, int productId) 
			throws IllegalStateException, IOException {
		
		String picturePattern = productId + PictureType.PRODUCT.getPictureType();
		List<File> existingPictures = getCertainImgNames(productId);
		savePictureOnDisk(uploadForm, picturePattern, existingPictures);
	}
	
	
	private void savePictureOnDisk(FileUpload uploadForm, String picturePattern, List<File> existingPictures) 
									throws IllegalStateException, IOException {
		
		//list of files that admin has added
		List<MultipartFile> newPictures = uploadForm.getFiles();
		
		if (null != newPictures && newPictures.size() > 0) {
			int i = 1;
			for (MultipartFile multipartFile : newPictures) {
				String fileName = multipartFile.getOriginalFilename();
				
				if (!"".equalsIgnoreCase(fileName)) {
					for (File f : existingPictures) {
						if (f.getName().equals(picturePattern + i 
								+ this.getFileExtension(f.getName()))) {
							i++;
						}
					}
					multipartFile.transferTo(new File(this.productPicturePath + picturePattern + i 
							+ this.getFileExtension(fileName)));	
					i++;
				}
			}
		}
	}

	
	/**
	 * Delete current picture from disk
	 * @param pictureName - String name of file
	 */
	public void deletePicture(String pictureName) {
		File img = new File(this.productPicturePath + pictureName);
		img.delete();
	}
	
	/**Deleting all pictures for product with current id
	 * @param productId
	 */
	public void deleteAllProductPictures(int productId) {
		this.getCertainImgNames(productId).forEach(p -> this.deletePicture(p.getName()));
	}
	
	/**Return array of byte for correct images displaying
	 * @param imgName
	 * @return
	 * @throws IOException
	 */
	public byte[] getPicture(String imgName) throws IOException {
	        File serverFile = new File(productPicturePath + imgName);
	        return Files.readAllBytes(serverFile.toPath());
    }
	
	
	/**Returns list of pictures for Products by productId
	 * @param productId - Id of product
	 * @return List of Files
	 */
	public List<File> getCertainImgNames(int productId) {
		String picturePattern = productId + PictureType.PRODUCT.getPictureType() + ".*";
		return this.getCertainImgNames(picturePattern);
	}
	
	
	/**
	 * Returns list of pictures for Articles or Banner
	 * @param picType - Enumeration that specifies type of returned pictures
	 * @return List of Files
	 */
	public List<File> getCertainImgNames(PictureType picType) {
		String picturePattern;
		switch (picType) {
			case ARTICLE:
				picturePattern = PictureType.ARTICLE.getPictureType() + ".*";
				break;
			case BANNER:
				picturePattern = PictureType.BANNER.getPictureType() + ".*";
				break;
			default:
				picturePattern = ".*";
				break;
		}
		return this.getCertainImgNames(picturePattern);
	}
	
	
	/**
	 * Returns list of pictures by particular pattern
	 * @param picturePattern - enum
	 * @return List of File objects
	 */
	private List<File> getCertainImgNames(String picturePattern) {
		return getAllPictures().stream().filter(f -> f.getName()
				.matches(picturePattern)).collect(Collectors.toList());
	}
	
	
	/**
	 * Return array of all pictures in folder
	 * @return File[]
	 */
	private List<File> getAllPictures() {
		File[] files = new File(this.productPicturePath).listFiles();
		return Arrays.asList(files);
	}
	
	
	private String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}


}
