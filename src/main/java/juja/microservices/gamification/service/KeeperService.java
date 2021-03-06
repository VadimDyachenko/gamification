package juja.microservices.gamification.service;

import juja.microservices.gamification.dao.KeeperRepository;
import juja.microservices.gamification.entity.Keeper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class KeeperService {
    @Inject
    private KeeperRepository keeperRepository;

    public List<Keeper> getKeepers() {
        return keeperRepository.getKeepers();
    }
}