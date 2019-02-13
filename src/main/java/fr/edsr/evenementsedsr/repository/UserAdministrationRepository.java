package fr.edsr.evenementsedsr.repository;

import fr.seblaporte.springsocial.repository.UserRepository;

public interface UserAdministrationRepository extends UserRepository {

    Boolean existsByEmail(String email);
}
