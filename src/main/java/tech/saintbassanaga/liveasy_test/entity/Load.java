package tech.saintbassanaga.liveasy_test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payload")
public class Load {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String loadingPoint;
    private String unloadingPoint;
    private String productType;
    private String truckType;
    private String noOfTrucks;
    private Long weight;
    private String comment;
    private UUID shipperId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

}