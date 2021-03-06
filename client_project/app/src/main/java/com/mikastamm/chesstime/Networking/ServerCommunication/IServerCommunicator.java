package com.mikastamm.chesstime.Networking.ServerCommunication;

public interface IServerCommunicator {
    void findGame(String passwordToken);
    void updateFirebaseToken(String passwordToken, String firebaseToken);
    void sendMove(String passwordToken, String gameId, String fromField, String toField);
    void register(String username);
}
