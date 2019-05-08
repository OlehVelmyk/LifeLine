package com.scenario_projects.lifeline_front_stage.utils;

import com.scenario_projects.lifeline_front_stage.dataProvider.PathToReportZipFile;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class DeleteReportZipFile {

    public static void deleteZipFile() {
        try {
            Files.deleteIfExists(Paths.get(PathToReportZipFile.reportZipFile));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty");
        } catch (IOException e) {
            System.out.println("Invalid permissions");
        }
        System.out.println("Deletion successful.");
    }
}
