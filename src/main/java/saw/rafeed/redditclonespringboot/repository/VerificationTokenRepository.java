package saw.rafeed.redditclonespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import saw.rafeed.redditclonespringboot.model.VerificationToken;
import java.util.Optional;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
    Optional<VerificationToken> findByToken(String token);
}
