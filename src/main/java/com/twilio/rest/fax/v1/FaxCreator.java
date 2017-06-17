/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.fax.v1;

import com.twilio.base.Creator;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

public class FaxCreator extends Creator<Fax> {
    private final String to;
    private final URI mediaUrl;
    private Fax.Quality quality;
    private URI statusCallback;
    private String from;
    private String sipAuthUsername;
    private String sipAuthPassword;
    private Boolean storeMedia;

    /**
     * Construct a new FaxCreator.
     * 
     * @param to The phone number or SIP address to send the fax to
     * @param mediaUrl URL that points to the fax media
     */
    public FaxCreator(final String to, 
                      final URI mediaUrl) {
        this.to = to;
        this.mediaUrl = mediaUrl;
    }

    /**
     * The quality setting to use for this fax. One of `standard`, `fine` or
     * `superfine`..
     * 
     * @param quality The quality of this fax
     * @return this
     */
    public FaxCreator setQuality(final Fax.Quality quality) {
        this.quality = quality;
        return this;
    }

    /**
     * The URL that Twilio will request when the status of the fax changes..
     * 
     * @param statusCallback URL for fax status callbacks
     * @return this
     */
    public FaxCreator setStatusCallback(final URI statusCallback) {
        this.statusCallback = statusCallback;
        return this;
    }

    /**
     * The URL that Twilio will request when the status of the fax changes..
     * 
     * @param statusCallback URL for fax status callbacks
     * @return this
     */
    public FaxCreator setStatusCallback(final String statusCallback) {
        return setStatusCallback(Promoter.uriFromString(statusCallback));
    }

    /**
     * The phone number to use as the caller id, E.164-formatted. If using a phone
     * number, it must be a Twilio number or a verified outgoing caller id for your
     * account. If sending to a SIP address, this can be any alphanumeric string
     * (plus the characters `+`, `_`, `.`, and `-`) to use in the From header of the
     * SIP request..
     * 
     * @param from Twilio number from which to originate the fax
     * @return this
     */
    public FaxCreator setFrom(final String from) {
        this.from = from;
        return this;
    }

    /**
     * The username to use for authentication when sending to a SIP address..
     * 
     * @param sipAuthUsername Username for SIP authentication
     * @return this
     */
    public FaxCreator setSipAuthUsername(final String sipAuthUsername) {
        this.sipAuthUsername = sipAuthUsername;
        return this;
    }

    /**
     * The password to use for authentication when sending to a SIP address..
     * 
     * @param sipAuthPassword Password for SIP authentication
     * @return this
     */
    public FaxCreator setSipAuthPassword(final String sipAuthPassword) {
        this.sipAuthPassword = sipAuthPassword;
        return this;
    }

    /**
     * Whether or not to store a copy of the sent media on Twilio's servers for
     * later retrieval (defaults to `true`).
     * 
     * @param storeMedia Whether or not to store media
     * @return this
     */
    public FaxCreator setStoreMedia(final Boolean storeMedia) {
        this.storeMedia = storeMedia;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Fax
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Fax create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.FAX.toString(),
            "/v1/Faxes",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Fax creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Fax.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (to != null) {
            request.addPostParam("To", to);
        }

        if (mediaUrl != null) {
            request.addPostParam("MediaUrl", mediaUrl.toString());
        }

        if (quality != null) {
            request.addPostParam("Quality", quality.toString());
        }

        if (statusCallback != null) {
            request.addPostParam("StatusCallback", statusCallback.toString());
        }

        if (from != null) {
            request.addPostParam("From", from);
        }

        if (sipAuthUsername != null) {
            request.addPostParam("SipAuthUsername", sipAuthUsername);
        }

        if (sipAuthPassword != null) {
            request.addPostParam("SipAuthPassword", sipAuthPassword);
        }

        if (storeMedia != null) {
            request.addPostParam("StoreMedia", storeMedia.toString());
        }
    }
}