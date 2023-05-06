package br.com.wrn.cryptowallet.repository;

import br.com.wrn.cryptowallet.entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoRepository extends JpaRepository<CryptoEntity, Long> {
    Optional<CryptoEntity> findByAsset(String asset);
}
