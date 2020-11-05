package phoneseller;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RewardRepository extends PagingAndSortingRepository<Reward, Long>{

    Optional<Reward> findByOrderId(Long orderId);

}