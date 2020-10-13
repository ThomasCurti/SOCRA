package com.mti.profile.domain.service;

import com.mti.profile.domain.entity.ProfileEntity;
import com.mti.profile.persistence.repository.ProfileRepository;
import com.mti.profile.utils.IterableUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProfileExcelService {

    private final ProfileRepository profileRepository;

    /**
     * Constructor
     * @param profileRepository repository injected
     * */
    public ProfileExcelService(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * get all profiles and parse them as email
     * @return HSSFWorkbook which is the object representing an excel file
     * */
    public HSSFWorkbook getAllProfilesAsExcel(){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Profiles");
        var profileEntityList = IterableUtils.toList(profileRepository.findAll());
        var listService = profileEntityList.stream()
                .sorted()
                .map(entity -> new ProfileEntity(entity.getId(),
                        entity.getName(),
                        entity.getFirstname(),
                        entity.getTown(),
                        entity.getTitle(),
                        entity.getExperience(),
                        entity.getFormation(),
                        entity.getTechnicalSkills(),
                        entity.getStudy()))
                .collect(Collectors.toList());

        // One variable for row / column
        Row row;
        Cell cell;

        // 1st line
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("Nombre de profils");

        // 2nd line
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(listService.size());

        // 3rd line empty and map 4th line
        row = sheet.createRow(3);
        // 1st column
        cell = row.createCell(0);
        cell.setCellValue("Nom");
        // 2nd column
        cell = row.createCell(1);
        cell.setCellValue("Prenom");
        // 3rd column
        cell = row.createCell(2);
        cell.setCellValue("Ville");
        // 4th column
        cell = row.createCell(3);
        cell.setCellValue("Titre");

        int rowNum = 3;
        // Start mapping profile from line 5 to bottom
        for (ProfileEntity entity: listService) {
            ++rowNum;
            row = sheet.createRow(rowNum);

            // 1st column
            cell = row.createCell(0);
            cell.setCellValue(entity.name);

            // 2nd column
            cell = row.createCell(1);
            cell.setCellValue(entity.firstname);

            // 3rd column
            cell = row.createCell(2);
            cell.setCellValue(entity.town);

            // 4th column
            cell = row.createCell(3);
            cell.setCellValue(entity.title);
        }
        return workbook;
    }
}
