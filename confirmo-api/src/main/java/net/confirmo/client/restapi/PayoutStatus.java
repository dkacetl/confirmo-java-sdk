package net.confirmo.client.restapi;

public enum PayoutStatus {

    prepared,   // - new payout
    confirmed,  // - Exchange rate has been accepted by merchant
    sending,    // - payout prepared to be sent. Payouts are send on regular basis
    done,       // - payment was successful and txid is known
    expired;    // - payout that has not been confirmed in given time.

}
