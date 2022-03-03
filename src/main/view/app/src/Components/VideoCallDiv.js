import React, { useEffect, useRef, useState } from "react";
import SimplePeer, { Instance, SignalData } from "simple-peer";
import Button from '@mui/material/Button';
import "../CSS/VideoCall.css";
import {Box, Container} from "@mui/material";


const webSocketConnection = new WebSocket("ws://localhost:8080/videochat");

export const VideoCallDiv = () => {
  const videoSelf = useRef(null);
  const videoCaller = useRef(null);
  const [connectionStatus, setConnectionStatus] = useState(null);
  const [offerSignal, setOfferSignal] = React.useState(SignalData);
  const [simplePeer, setSimplePeer] = useState(Instance);

  useEffect(() => {
    webSocketConnection.onmessage = (message) => {
      const payload = JSON.parse(message.data);
      if (payload?.type === "offer") {
        setOfferSignal(payload);
        setConnectionStatus("RECEIVING");
      } else if (payload?.type === "answer") simplePeer.signal(payload);
    };
  }, [simplePeer]);

  const sendOrAcceptInvitation = (isInitiator, offer) => {
    navigator.mediaDevices.getUserMedia({ video: true, audio: false }).then((mediaStream) => {
      const video = videoSelf.current;
      video.srcObject = mediaStream;
      video.play();

      const sp = new SimplePeer({
        trickle: false,
        initiator: isInitiator,
        stream: mediaStream,
      });

      if (isInitiator) setConnectionStatus("OFFERING");
      else offer && sp.signal(offer);

      sp.on("signal", (data) => webSocketConnection.send(JSON.stringify(data)));
      sp.on("connect", () => setConnectionStatus("CONNECTED"));
      sp.on("stream", (stream) => {
        const video = videoCaller.current;
        video.srcObject = stream;
        video.play();
      });
      setSimplePeer(sp);
    });
  };

  return (
    <div>
      <div className="web-rtc-page">
        {connectionStatus === null && <Button onClick={() => sendOrAcceptInvitation(true)}>JOIN CALL</Button>}
        {connectionStatus === "OFFERING" && <div className="loader"/>}
        {connectionStatus === "RECEIVING" && (
            <Button onClick={() => sendOrAcceptInvitation(false, offerSignal)}>ACCEPT PARTICIPANT</Button>
        )}
        <div className="video-container">
          <video ref={videoSelf} className="video-block" />
          <video ref={videoCaller} className="video-block" />
        </div>
      </div>
    </div>
  );
};
