INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('client_app_id', '', 'client_app_secret', 'read,write',
                    'client_credentials,password,authorization_code,refresh_token',
                    '',
                    '', NULL, NULL, '{}', 'true');