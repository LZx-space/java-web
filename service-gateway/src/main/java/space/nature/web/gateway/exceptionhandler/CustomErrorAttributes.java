package space.nature.web.gateway.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * @author LZx
 * <p>
 * 重写默认的错位信息处理类的{@link #getErrorAttributes(ServerRequest, boolean)}用于调整返回的数据，比如JSON数据对应的MAP
 */
@Slf4j
public class CustomErrorAttributes extends DefaultErrorAttributes {

    public CustomErrorAttributes(boolean includeException) {
        super(includeException);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        return super.getErrorAttributes(request, includeStackTrace);
    }
}