package com.tictactoe.gameserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Client class for the oAuth entity.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "oauth_client_details")
public class Client {

    private static final long serialVersionUID = 1L;

    public Client(String clientId, String resourceIds, String clientSecret, String scope, String authorizedGrantTypes,
                  String webServerRedirectURI, String authorities, Long accessTokenValidity,
                  Long refresh_token_validity, String additionalInformation, String autoApprove, Long version) {
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.webServerRedirectURI = webServerRedirectURI;
        this.authorities = authorities;
        this.accessTokenValidity = accessTokenValidity;
        this.refresh_token_validity = refresh_token_validity;
        this.additionalInformation = additionalInformation;
        this.autoApprove = autoApprove;
        this.version = version;
    }

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "resource_ids")
    private String resourceIds;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scope")
    private String scope;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectURI;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private Long accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Long refresh_token_validity;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "autoapprove")
    private String autoApprove;

    @Version
    private Long version;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdOn;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
