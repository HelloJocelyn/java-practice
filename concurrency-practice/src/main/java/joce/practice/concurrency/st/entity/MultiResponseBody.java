package joce.practice.concurrency.st.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author ts-jiajia.hu
 * @Date 2019/10/07
 */
@Data
public class MultiResponseBody {
    private List<String> subjects;
    private Map<String,Object> backend_response;
    private Artifacts artifacts;


}
