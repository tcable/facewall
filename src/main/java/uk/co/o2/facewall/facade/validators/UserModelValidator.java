package uk.co.o2.facewall.facade.validators;

import uk.co.o2.facewall.data.TeamRepository;
import uk.co.o2.facewall.data.dto.PersonInformation;
import uk.co.o2.facewall.domain.Team;
import uk.co.o2.facewall.facade.AccountsFacade;
import uk.co.o2.facewall.model.UserModel;

import java.util.InputMismatchException;
import java.util.List;

import static uk.co.o2.facewall.application.Facewall.facewall;
import static uk.co.o2.facewall.data.dto.PersonInformation.newPersonInformation;
import static uk.co.o2.facewall.domain.NoTeam.noTeam;
import static uk.co.o2.facewall.domain.Query.newExactQuery;
import static java.util.UUID.randomUUID;

public class UserModelValidator {

    private final TeamRepository teamRepository;

    public UserModelValidator(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public ValidatedUserModel validate(UserModel userModel) {
        PersonInformation personInformation = createPersonInformation(userModel);
        Team team = createTeam(userModel);
        return new ValidatedUserModel(personInformation, team);
    }

    private Team createTeam(UserModel userModel) {
        Team team;
        List<Team> teamsList = teamRepository.queryTeams(newExactQuery(userModel.team));
        if(teamsList.isEmpty()) {
            team = noTeam();
        } else {
            team = teamsList.get(0);
        }

        return team;
    }

    private PersonInformation createPersonInformation(UserModel userModel) {
        return newPersonInformation()
                .withId(randomUUID().toString()) // TODO: change to user-chosen permalink (or email)
                .named(userModel.name)
                .withPicture(userModel.imgUrl)
                .withEmail(userModel.email)
                .withPassword(userModel.password)
                .withRole(userModel.role)
                .withScrum(userModel.scrum)
                .withDetails(userModel.details)
                .withLocation(userModel.location)
                .withOfficeLocation(userModel.officeLocation)
                .build();
    }
}
