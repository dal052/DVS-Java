package com.ucsd.globalties.dvs.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.highgui.Highgui;

import lombok.Setter;

import com.ucsd.globalties.dvs.core.Photo.PhotoType;
import com.ucsd.globalties.dvs.core.excel.ExcelDataGenerator;


public class Controller {
	
  @Setter
  private Patient patient = null;
  private List<Patient> sessionPatients = null;
  
  public void setPatientPhotos(String hFilePath, String vFilePath) {
    List<Photo> photos = new ArrayList<Photo>();
    photos.add(new Photo(hFilePath, PhotoType.HORIZONTAL));
    photos.add(new Photo(vFilePath, PhotoType.VERTICAL));
    patient.setPhotos(photos);
  }
  
  public void finalizePatient() {
    if (sessionPatients == null) {
      sessionPatients = new ArrayList<Patient>();
    }
    sessionPatients.add(patient);
    patient = null;
  }
  
  public void diagnose() {
    patient.diagnose();
  }
  
  public Map<EyeDisease, String> getRecords() {
    return patient.getMedicalRecord();
  }

  public void exportData() {
    ExcelDataGenerator.exportPatientData(sessionPatients);
  }
  
  public Map<String, String> detectAll() {
    Map<String,String> detected = new HashMap<String,String>();
    if (patient.getPhotos().get(0).getLeftEye() != null) {
      Highgui.imwrite(Main.OUTPUT_FILE + "left_eye_horizontal.jpg",patient.getPhotos().get(0).getLeftEye().getMat());
      detected.put("left_eye_horizontal", Main.OUTPUT_FILE + "left_eye_horizontal.jpg");
    }
    if (patient.getPhotos().get(0).getLeftEye().getPupil() != null) {
      patient.getPhotos().get(0).getLeftEye().getPupil().getWhiteDot();
      Highgui.imwrite(Main.OUTPUT_FILE + "left_eye_pupil_horizontal.jpg",patient.getPhotos().get(0).getLeftEye().getPupil().getMat());
      detected.put("left_eye_pupil_horizontal", Main.OUTPUT_FILE + "left_eye_pupil_horizontal.jpg");
    }
    if (patient.getPhotos().get(0).getRightEye() != null) {
      Highgui.imwrite(Main.OUTPUT_FILE + "right_eye_horizontal.jpg",patient.getPhotos().get(0).getRightEye().getMat());
      detected.put("right_eye_horizontal", Main.OUTPUT_FILE + "right_eye_horizontal.jpg");
    }
    if (patient.getPhotos().get(0).getRightEye().getPupil() != null) {
//      patient.getPhotos().get(0).getRightEye().getPupil().getWhiteDot();
      Highgui.imwrite(Main.OUTPUT_FILE + "right_eye_pupil_horizontal.jpg",patient.getPhotos().get(0).getRightEye().getPupil().getMat());
      detected.put("right_eye_pupil_horizontal", Main.OUTPUT_FILE + "right_eye_pupil_horizontal.jpg");
    }
    
    if (patient.getPhotos().get(1).getLeftEye() != null) {
      Highgui.imwrite(Main.OUTPUT_FILE + "left_eye_vertical.jpg",patient.getPhotos().get(1).getLeftEye().getMat());
      detected.put("left_eye_vertical", Main.OUTPUT_FILE + "left_eye_vertical.jpg");
    }
    if (patient.getPhotos().get(1).getLeftEye().getPupil() != null) {
      Highgui.imwrite(Main.OUTPUT_FILE + "left_eye_pupil_vertical.jpg",patient.getPhotos().get(1).getLeftEye().getPupil().getMat());
      detected.put("left_eye_pupil_vertical", Main.OUTPUT_FILE + "left_eye_pupil_vertical.jpg");
    }
    if (patient.getPhotos().get(1).getRightEye() != null) {
      Highgui.imwrite(Main.OUTPUT_FILE + "right_eye_vertical.jpg",patient.getPhotos().get(1).getRightEye().getMat());
      detected.put("right_eye_vertical", Main.OUTPUT_FILE + "right_eye_vertical.jpg");
    }
    if (patient.getPhotos().get(1).getRightEye().getPupil() != null) {
      Highgui.imwrite(Main.OUTPUT_FILE + "right_eye_pupil_vertical.jpg",patient.getPhotos().get(1).getRightEye().getPupil().getMat());
      detected.put("right_eye_pupil_vertical", Main.OUTPUT_FILE + "right_eye_pupil_vertical.jpg");
    }
    
    return detected;
  }

}
