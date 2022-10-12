package com.bt.marketplace.partnercredentials.controller.common;

public class ValidationMessage {

    public static final String FIELD_NAME_CUSTOMER_ID = "customerId";
    public static final String FIELD_NAME_ISV_ID = "isvId";
    public static final String FIELD_NAME_TENET_ID = "credentials.tenetId";
    public static final String FIELD_NAME_USER_ID = "credentials.userId";
    public static final String FIELD_NAME_CLIENT_ID = "credentials.organisationSecrets.clientId";
    public static final String FIELD_NAME_CLIENT_SECRET = "credentials.organisationSecrets.clientSecret";
    public static final String FIELD_NAME_ACCESS_TOKEN = "credentials.organisationSecrets.accessToken";
    public static final String FIELD_NAME_REFRESH_TOKEN = "credentials.organisationSecrets.refreshToken";

    public static final String MSG_DSCR_MUST_NOT_BE_EMPTY = "Must not be empty";
    public static final String MSG_DSCR_CREDENTIALS_MUST_NOT_BE_NULL = "Credentials must be provided." ;
    public static final String MSG_DSCR_ORG_SECRETS_MUST_NOT_BE_NULL = "OrganisationSecrets must be provided." ;
}
