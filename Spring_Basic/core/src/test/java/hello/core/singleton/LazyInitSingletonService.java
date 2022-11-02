package hello.core.singleton;

public class LazyInitSingletonService {

    // 1. private 생성자
    private LazyInitSingletonService(){

    }

    // 2. 객체 생성을 담당하는 static Inner class
    private static class SettingsHolder{
        private static final LazyInitSingletonService instance =
                new LazyInitSingletonService();
    }

    public static LazyInitSingletonService getInstance() {
        return SettingsHolder.instance;
    }

} // end class
