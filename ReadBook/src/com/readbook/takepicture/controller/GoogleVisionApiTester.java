package com.readbook.takepicture.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

	public class GoogleVisionApiTester {

		public String getImage(String filePath) throws IOException{			
			Path path = Paths.get(filePath);
			byte[] data = Files.readAllBytes(path);

			List<AnnotateImageRequest> requests = new ArrayList<AnnotateImageRequest>();
			
			ByteString imgBytes = ByteString.copyFrom(data);
			
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest request =
					AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);
			
			StringBuffer sb = new StringBuffer();
			
			String str ="";
			
			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
				List<AnnotateImageResponse> responses = response.getResponsesList();
				
				for (AnnotateImageResponse res : responses) {
					if (res.hasError()) {
						System.out.printf("Error: %s\n", res.getError().getMessage());
						String error = "에러 났다";
						return error;
					}
					
					System.out.println("@@@@@@@@@@@Text : ");
			    	System.out.println(res.getTextAnnotationsList().get(0).getDescription());
			    	str = res.getTextAnnotationsList().get(0).getDescription();
				}
			}
			
			//String str = sb.toString();
			//System.out.println("^^^^^^^str:"+str);
			//String[] array = str.split(",");

			return str;
		}
	}