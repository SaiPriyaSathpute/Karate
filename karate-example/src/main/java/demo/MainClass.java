package demo;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

public class MainClass {
    public static void main(String[] args){
        String featureFilePath=args[0];
        if (featureFilePath == null || featureFilePath.isEmpty()) {
            System.out.println("Please provide the path of the feature file.");
            System.exit(1);
        }

        Results results = Runner.path(featureFilePath)
                .outputCucumberJson(true)
                .parallel(5);

        generateReport(results.getReportDir());

        Assertions.assertTrue(results.getFailCount() == 0, results.getErrorMessages());
    }
    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
