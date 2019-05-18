package cn.itsource.maomaogo.client;//package cn.itsource.aigou.client;
//
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RedisClientFallBackFactory implements FallbackFactory<RedisClient> {
//
//    public RedisClient create(Throwable throwable) {
//        return new RedisClient() {
//            public void set(String key, String value) {
//                //...........
//            }
//
//            public String get(String key) {
//                return "[]";
//            }
//        };
//    }
//}
