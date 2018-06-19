import java.util.ArrayList;

public class DSSample {
    private static ConcurrentMultiHashMap concurrentMultiHashMap;
    public static void main(String[] args) throws Exception {
        System.out.println(" Welcome to DSSample :: ");

        concurrentMultiHashMap = new ConcurrentMultiHashMap<String,ArrayList>();;

        Thread t1=new Thread(new Thread1(concurrentMultiHashMap));
        t1.setName("Thread1");
        t1.start();
//        t1.sleep(20);
        Thread t2=new Thread(new Thread1(concurrentMultiHashMap));
        t2.setName("Thread2");
        t2.start();
//        t2.sleep(20);
        Thread t3=new Thread(new Thread1(concurrentMultiHashMap));
        t3.setName("Thread3");
        t3.start();
//        t3.sleep(20);
        Thread t4=new Thread(new Thread1(concurrentMultiHashMap));
        t4.setName("Thread4");
        t4.start();
//        t4.sleep(20);
    }
}

class Thread1 implements Runnable{

    ConcurrentMultiHashMap concurrentMultiHashMap;
    public Thread1(ConcurrentMultiHashMap concurrentMultiHashMap){
        System.out.println(" Thread1 "+ Thread.currentThread().getName() +" :: "+concurrentMultiHashMap.size());
        this.concurrentMultiHashMap = concurrentMultiHashMap;
    }

    @Override
    public void run() {
        addValues(concurrentMultiHashMap);
        removeValue(concurrentMultiHashMap);
    }

    public void addValues(ConcurrentMultiHashMap chm){
        for(int i=0;i<100;i++){
            System.out.println("addValues " + Thread.currentThread().getName() +" ====> :: "+concurrentMultiHashMap.size());
            chm.putAll(i+"",new ArrayList());
        }
    }

    public void removeValue(ConcurrentMultiHashMap chm){
        for(int i=0;i<100;i++){
            System.out.println("removeValue " + Thread.currentThread().getName() + " Thread1 ====> :: "+concurrentMultiHashMap.size());
            chm.removeAll(i+"");
        }
    }
}
