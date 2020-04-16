//package com.ytooo.file;
//
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.*;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.util.Date;
//import java.util.List;
//
//public class OssUtil {
//	private static String ENDPOINT;
//	//阿里云API的密钥Access Key ID
//	private static String ACCESS_KEY_ID;
//	//阿里云API的密钥Access Key Secret
//	private static String ACCESS_KEY_SECRET;
//
//	//init static datas
//	static{
//
//		ENDPOINT="oss-cn-beijing.aliyuncs.com";
//		ACCESS_KEY_ID="LTAIv1p3C9thzWYm";
//		ACCESS_KEY_SECRET="9MWUnUnCaR31ZVncqrrguTeNB55Zn6";
//	}
//	/**
//	 * 获取阿里云OSS客户端对象
//	 * */
//	public static final OSSClient getOSSClient(){
//		return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
//	}
//
//	/**
//	 * 新建Bucket  --Bucket权限:私有
//	 * @param bucketName bucket名称
//	 * @return true 新建Bucket成功
//	 * */
//	public static final boolean createBucket(OSSClient client, String bucketName){
//		List<Bucket> buckets = client.listBuckets();
//		for( Bucket bucket : buckets){
//			if(bucketName.equals(bucket.getName())){
//				return true ;
//			}
//		}
//		//创建bucket
//		Bucket bucket = client.createBucket(bucketName);
//		client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
//		return bucketName.equals(bucket.getName());
//	}
//
//	/**
//	 * 删除Bucket
//	 * @param bucketName bucket名称
//	 * */
//	public static final void deleteBucket(OSSClient client, String bucketName){
//		client.deleteBucket(bucketName);
//		System.out.println("删除" + bucketName + "Bucket成功");
//	}
//
//	/**
//	 * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代
//	 * @param client OSS客户端
//	 * @param io 上传文件流
//	 * @param bucketName bucket名称
//	 * @param fileName 文件名
//	 * @return String 唯一MD5数字签名
//	 * */
//	public static final String uploadObjectIO(OSSClient client, InputStream io, String bucketName, String fileName,String doc) {
//		String resultStr = null;
//		try {
//			 ObjectMetadata objectMetadata = new ObjectMetadata();
//		      objectMetadata.setContentLength(io.available());
//		      objectMetadata.setCacheControl("no-cache");
//		      objectMetadata.setHeader("Pragma", "no-cache");
//		      objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
//		      objectMetadata.setContentDisposition("inline;filename=" + fileName);
//		      //上传文件
//			  PutObjectResult putResult = client.putObject(bucketName,  doc + fileName, io);
//			  //解析结果
//			  resultStr = putResult.getETag();
//		} catch (Exception e) {
//			System.out.println("上传阿里云OSS服务器异常." + e.getMessage());
//		}
//		return resultStr;
//	}
//
//    /**
//     * 根据key获取OSS服务器上的文件输入流
// 	 * @param client OSS客户端
// 	 * @param bucketName bucket名称
// 	 * @param diskName 文件路径
// 	 * @param key Bucket下的文件的路径名+文件名
//     */
//     public static final InputStream getOSS2InputStream(OSSClient client, String bucketName, String diskName, String key){
//		OSSObject ossObj = client.getObject(bucketName, diskName + key);
//		return ossObj.getObjectContent();
//     }
//
//   /**
//    * 根据key删除OSS服务器上的文件
//  	* @param client OSS客户端
//  	* @param bucketName bucket名称
//  	* @param diskName 文件路径
//  	* @param key Bucket下的文件的路径名+文件名
//    */
//	  public static void deleteFile(OSSClient client, String bucketName, String diskName, String key){
//	  	client.deleteObject(bucketName, diskName + key);
//	  	System.out.println("删除" + bucketName + "下的文件" + diskName + key + "成功");
//	  }
//	  /**
//	   * 生成文件链接
//	   * @param client
//	   * @param key
//	   * @param bucketName
//	   * @return
//	   */
//	  public static String generatePresignedUrlRequest(OSSClient client,  String key,String bucketName){
//		   Date expires = new Date(new Date().getTime()+ 10*365*24*3600*1000);
//			GeneratePresignedUrlRequest generatePresignedUrlRequest =  new GeneratePresignedUrlRequest(bucketName, key);
//			generatePresignedUrlRequest.setExpiration(expires);
//		    URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
//		    String  allUrlString = url.toString();//完整路径，带验证的
//		    if(allUrlString.indexOf("?") !=-1){
//		    	return allUrlString.substring(0, allUrlString.indexOf("?"));
//		    }else{
//		    	return allUrlString;
//		    }
//	  }
//	  /**
//	     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
//	     * @param fileName 文件名
//	     * @return 文件的contentType
//	     */
//	     public static final String getContentType(String fileName){
//	    	String fileExtension = fileName.substring(fileName.lastIndexOf("."));
//	    	if("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
//	    	if("gif".equalsIgnoreCase(fileExtension)) return "image/gif";
//	    	if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)  || "png".equalsIgnoreCase(fileExtension) ) return "image/jpeg";
//	    	if("html".equalsIgnoreCase(fileExtension)) return "text/html";
//	    	if("txt".equalsIgnoreCase(fileExtension)) return "text/plain";
//	    	if("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
//	    	if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) return "application/vnd.ms-powerpoint";
//	    	if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) return "application/msword";
//	    	if("xml".equalsIgnoreCase(fileExtension)) return "text/xml";
//	        return "text/html";
//	     }
//
//
//	     public static void getALLFile(OSSClient client,String bucketName){
//		   	  ObjectListing objectListing = client.listObjects(bucketName);
//			  for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//			      System.out.println(" - " + objectSummary.getKey() + "  " +
//			              "(size = " + objectSummary.getSize() + ")");
//			  }
//	 	  }
//
//
//}
