package org.twin.infrastructure.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.twin.domain.model.Enums.ERoles;
import org.twin.domain.model.Role;
import org.twin.infrastructure.repository.RoleRepository;

@Component
public class RolesInitializer implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initializeRoles();
    }

    private void initializeRoles() {
        Role roleUser = new Role(1, ERoles.ROLE_USER);
        Role roleAdmin = new Role(2, ERoles.ROLE_ADMIN);
        Role roleModerator = new Role(3, ERoles.ROLE_MODERATOR);

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleModerator);
        System.out.println("Salvou");
    }
}