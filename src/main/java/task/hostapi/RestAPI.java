package task.hostapi;

import org.springframework.web.bind.annotation.*;
import task.hostapi.DTO.Host;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestAPI {

    private List<Host>hostList = new ArrayList<>();

    //전체 조회
    @GetMapping("/hosts")
    public List<Host> GetAll(){
        return hostList;
    }

    //json을 받기때문에 @RequestBody사용
    @PostMapping("/add")
    public String Add(@RequestBody Host host){
        hostList.add(host);
        return "Add";
    }

    //@PathVariable을 사용하면 동적 path를 자동 파싱한다.
    @PutMapping("/update/{name}")
    public String Update(@RequestBody Host host2, @PathVariable int name){

        Host find_host = hostList.stream()
                .filter(host -> host.getName() == name)
                .findAny()
                .orElse(null);

        if(find_host != null){
            find_host.setName(host2.getName());
            find_host.setAddress(host2.getAddress());
            return "Success";

        }
        return "not valid";


    }

    @DeleteMapping("/delete/{name}")
    public String Delete(@PathVariable int name){

        Host find_host = hostList.stream()
                .filter(host -> host.getName() == name)
                .findAny()
                .orElse(null);

        if(find_host != null){
            hostList.remove(find_host);
            return "success";
        }

        return "not valid";
    }

}



