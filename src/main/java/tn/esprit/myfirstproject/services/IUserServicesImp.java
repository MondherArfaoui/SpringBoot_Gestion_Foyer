package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.repositories.IUserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class IUserServicesImp implements IUserServices {

    private final IUserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String s) {
                return userRepository.findByEmail(s).orElseThrow(() -> new RuntimeException("User not found"));
            }
        };
    }
}
