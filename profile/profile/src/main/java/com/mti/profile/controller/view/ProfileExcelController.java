package com.mti.profile.controller.view;

import com.mti.profile.domain.service.ProfileExcelService;
import com.mti.profile.domain.service.ProfileService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/excel")
public class ProfileExcelController {

    private final ProfileExcelService profileExcelService;

    /**
     * Constructor
     * @param profileExcelService service injected
     * */
    public ProfileExcelController(final ProfileExcelService profileExcelService) {
        this.profileExcelService = profileExcelService;
    }

    /**
     * Return all profiles in an excel file
     * @return ResponseEntity<Resource> response which is the excel file
     * */
    @GetMapping("/")
    public ResponseEntity<Resource> getAllProfilesAsExcel() {
        try {
            HSSFWorkbook workbook = profileExcelService.getAllProfilesAsExcel();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=profiles.xls")
                    .contentLength(byteArrayResource.contentLength())
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .body(byteArrayResource);
        } catch (IOException ignored){
            return null;
        }
    }
}
