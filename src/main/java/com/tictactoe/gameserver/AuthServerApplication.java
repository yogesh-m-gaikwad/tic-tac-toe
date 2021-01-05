/*
 * Copyright 2002-2020 the original author or authors.
 *
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */
package com.tictactoe.gameserver;

import com.tictactoe.gameserver.domain.*;
import com.tictactoe.gameserver.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Main class for the spring boot application
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@SpringBootApplication
public class AuthServerApplication {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionsRepository rolePermissionsRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Bean
    InitializingBean seedDatabase() {
        return () -> {
            if (clientRepository.count() == 0) {
                clientRepository.save(new Client("adminapp", "game/clientapp",
                        "{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi", "role_webclient",
                        "authorization_code,password,refresh_token,implicit", null,
                        null, 900L, 3600L, "{}", null,
                        1L));

                permissionRepository.save(new Permission(1L, "can_delete_user", 1L));
                permissionRepository.save(new Permission(2L, "can_create_user", 1L));
                permissionRepository.save(new Permission(3L, "can_update_user", 1L));
                permissionRepository.save(new Permission(4L, "can_read_user", 1L));

                roleRepository.save(new Role(1L, "role_admin", 1L));
                roleRepository.save(new Role(2L, "role_user", 1L));

                userRepository.save(new User(1L, "admin@example.com", "admin", passwordEncoder().encode("password")));
                userRepository.save(new User(2L, "user@example.com", "user", passwordEncoder().encode("password")));

                /* Following code will create demo users for testing game pool */
                for (int i = 1; i < 101; i++) {
                    userRepository.save(new User(2L, "user+" + i + "@example.com", "user-" + i, passwordEncoder().encode("password")));
                }

                rolePermissionsRepository.save(new RolePermissions(new RolePermissionsId(1L, 1L), 1L));
                rolePermissionsRepository.save(new RolePermissions(new RolePermissionsId(2L, 1L), 1L));
                rolePermissionsRepository.save(new RolePermissions(new RolePermissionsId(3L, 1L), 1L));
                rolePermissionsRepository.save(new RolePermissions(new RolePermissionsId(4L, 1L), 1L));
                rolePermissionsRepository.save(new RolePermissions(new RolePermissionsId(4L, 2L), 1L));

                userRolesRepository.save(new UserRoles(new UserRoleId(1L, 1L), 1L));
                userRolesRepository.save(new UserRoles(new UserRoleId(2L, 1L), 1L));
                userRolesRepository.save(new UserRoles(new UserRoleId(2L, 2L), 1L));
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
