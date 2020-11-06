package phoneseller;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Reward_table")
public class Reward {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Double point;
    private String process;

    @PrePersist
    public void onPrePersist() {
        System.out.println("Reward pre persist");
    }

    @PostPersist
    public void onPostPersist(){
        System.out.println(this.toString());
        System.out.println("Reward persist");

        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if("Payed".equals(process) && point > 0){
            // 결제 완료된 이벤트를 통해 프로모션 제공 완료 처리

            RewardCompleted rewardCompleted = new RewardCompleted();
            BeanUtils.copyProperties(this, rewardCompleted);
            rewardCompleted.publish();

            System.out.println("*** 프로모션 포인트 제공 완료 ***");
        } else if("PayCancelled".equals(process)){
            RewardCancelled rewardCancelled = new RewardCancelled();
            BeanUtils.copyProperties(this, rewardCancelled);
            rewardCancelled.publish();
            System.out.println("*** 결제 취소로 인한 프로모션 포인트 제공 회수 ***");
        }
    }

    @PreUpdate
    public void onPreUpdate(){
        System.out.println("reward pre update");
    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("reward post update");
    }

    @PreRemove
    public void onPreRemove(){
        System.out.println("reward pre remove");
    }

    @PostRemove
    public void onPostRemove(){
        System.out.println("reward post remove");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", point=" + point +
                ", process=" + process +
                '}';
    }
}
