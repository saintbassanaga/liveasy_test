package tech.saintbassanaga.liveasy_test.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.saintbassanaga.liveasy_test.entity.Load;
import tech.saintbassanaga.liveasy_test.repository.LoadRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoadService {
    private final LoadRepository loadRepository;

    public LoadService(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    /**
     *
     * @param load load Details Holder object
     * @return Success Message if create or an Error if it is not
     */

    public String createLoad(Load load){
        //load.setShipperId(UUID.randomUUID());
        loadRepository.save(load);
        return "Loads details added successfully ";
    }

    /**
     *
     * @param id used to find the Load
     * @return A found not just a message if not found
     */

    public Optional<Load> findById(UUID id){
        return loadRepository.findById(id);
    }

    /**
     *
     * @param uuid : shipper uuid used to find all load related with
     * @return A list of found Loads
     */

    public List<Load> payLoadList(UUID uuid){
        return loadRepository.findLoadsByShipperId(uuid);
    }

    /**
     *
     * @param uuid : it is an uuid of the load to be Updated
     * @param load it is the load change handler data that will be applied the founded load
     */

    public void updatePayLoad(UUID uuid, Load load){
        Load loadChecked = loadRepository.findById(uuid).orElseThrow(()->  new RuntimeException("PayLoad Not Found"));
        if (loadChecked != null){
            loadChecked.setLoadingPoint(load.getLoadingPoint());
            loadChecked.setUnloadingPoint(load.getUnloadingPoint());
            loadChecked.setProductType(load.getProductType());
            loadChecked.setTruckType(load.getTruckType());
            loadChecked.setNoOfTrucks(load.getNoOfTrucks());
            loadChecked.setWeight(load.getWeight());
            loadChecked.setComment(load.getComment());
            loadChecked.setDate(load.getDate());
            loadRepository.save(loadChecked);
        }
    }

    /**
     *
     * @param uuid it permits to find the load to be deleted
     * @implNote  That method find the load using the provided uuid and Delete if found \
     *          Or just return an Error if not found
     */

    @Transactional
    public void deletePayLoad(UUID uuid){
        Load load = loadRepository.findById(uuid).orElseThrow(()->new RuntimeException("There is not load with that Id"));
        if (load != null)
            loadRepository.deletePayLoadById(uuid);
    }
}
