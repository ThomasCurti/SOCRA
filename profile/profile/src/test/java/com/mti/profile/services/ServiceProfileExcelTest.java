package com.mti.profile.services;

import com.mti.profile.domain.service.ProfileExcelService;
import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceProfileExcelTest {

    @Autowired
    ProfileRepository repositoryProfile;

    ProfileExcelService profileExcelService;

    @Before
    public void Init() {
        repositoryProfile.deleteAll();
        profileExcelService = new ProfileExcelService(repositoryProfile);
    }

    @AfterEach
    public void Clear() {
        repositoryProfile.deleteAll();
    }

    //Feature 3.5 Get profiles as excel
    @Test
    public void get_all_profiles_as_excel_return_not_null(){
        HSSFWorkbook workbook = profileExcelService.getAllProfilesAsExcel();
        Assert.assertNotNull(workbook);
    }

    @Test
    public void get_all_profiles_as_excel_return_workbook_with_one_sheet(){
        HSSFWorkbook workbook = profileExcelService.getAllProfilesAsExcel();
        Assert.assertEquals(1, workbook.getNumberOfSheets());
        Assert.assertEquals("Profiles", workbook.getSheetName(0));
    }

    @Test
    public void get_all_profiles_as_excel_return_workbook_formatted_to_display_profiles(){
        ProfileModel p1 = new ProfileModel(0,
                "Test1",
                "test1",
                "town1",
                "title1",
                "experience1",
                "formation1",
                "technicalSkills1",
                "study1");
        ProfileModel p2 = new ProfileModel(0,
                "Test2",
                "test2",
                "town2",
                "title2",
                "experience2",
                "formation2",
                "technicalSkills2",
                "study2");

        repositoryProfile.saveAll(List.of(p1, p2));

        HSSFWorkbook workbook = profileExcelService.getAllProfilesAsExcel();
        Assert.assertEquals(1, workbook.getNumberOfSheets());
        HSSFSheet sheet = workbook.getSheetAt(0);

        // 1st line
        Assert.assertEquals("Nombre de profils", sheet.getRow(0).getCell(0).getStringCellValue());
        // 2nd line
        Assert.assertEquals(2, (int)sheet.getRow(1).getCell(0).getNumericCellValue());
        // 4eme line
        Assert.assertEquals("Nom", sheet.getRow(3).getCell(0).getStringCellValue());
        Assert.assertEquals("Prenom", sheet.getRow(3).getCell(1).getStringCellValue());
        Assert.assertEquals("Ville", sheet.getRow(3).getCell(2).getStringCellValue());
        Assert.assertEquals("Titre", sheet.getRow(3).getCell(3).getStringCellValue());
        // 5th line -> 1st profile
        Assert.assertEquals(p1.getName(), sheet.getRow(4).getCell(0).getStringCellValue());
        Assert.assertEquals(p1.getFirstname(), sheet.getRow(4).getCell(1).getStringCellValue());
        Assert.assertEquals(p1.getTown(), sheet.getRow(4).getCell(2).getStringCellValue());
        Assert.assertEquals(p1.getTitle(), sheet.getRow(4).getCell(3).getStringCellValue());
        // 6th line -> 1st profile
        Assert.assertEquals(p2.getName(), sheet.getRow(5).getCell(0).getStringCellValue());
        Assert.assertEquals(p2.getFirstname(), sheet.getRow(5).getCell(1).getStringCellValue());
        Assert.assertEquals(p2.getTown(), sheet.getRow(5).getCell(2).getStringCellValue());
        Assert.assertEquals(p2.getTitle(), sheet.getRow(5).getCell(3).getStringCellValue());
    }
}
