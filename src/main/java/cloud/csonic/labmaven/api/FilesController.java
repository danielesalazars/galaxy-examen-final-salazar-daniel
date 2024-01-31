package cloud.csonic.labmaven.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FilesController {


    @GetMapping("/{path}")
    public List<String> getFiles(@PathVariable("path")String dir) throws IOException {

        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    //.filter(file->Files.isDirectory(file))
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }

        /*Files.list(Paths.get(dir))
                .filter(file->Files.isDirectory(file))
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());*/
    }
}
