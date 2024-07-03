package deleyQueue;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;


public class CouponManager {
    private final DelayQueue<Coupon> couponQueue = new DelayQueue<>();

    public void addCoupons() throws ParseException {
        couponQueue.put(new Coupon("Vingraf", "29/01/2019", TimeUnit.SECONDS));
        couponQueue.put(new Coupon("Azaron", "12/02/2020", TimeUnit.SECONDS));
        couponQueue.put(new Coupon("Voricoazole", "07/04/2018", TimeUnit.SECONDS));
        couponQueue.put(new Coupon("Pan40", "16/03/1998", TimeUnit.SECONDS));
        couponQueue.put(new Coupon("Omanacortil", "19/07/2019", TimeUnit.SECONDS));
    }

    public void startCouponExpirationHandler() {
        new Thread(() -> {
            try {
                while (true) {
                    Coupon coupon = couponQueue.take(); 
                    System.out.println("Expired: " + coupon);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException, ParseException {
        CouponManager manager = new CouponManager();
        manager.addCoupons();
        manager.startCouponExpirationHandler();
        Thread.sleep(30000);  
    }
}