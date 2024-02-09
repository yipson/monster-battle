package co.fullstacklabs.battlemonsters.challenge.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateFightDTO {

    private Integer monsterAId;
    private Integer monsterBId;

}
