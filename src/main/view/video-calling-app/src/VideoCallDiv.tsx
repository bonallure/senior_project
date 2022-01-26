import { Button } from '@mui/material';
import * as React from 'react';
import { useRef } from 'react';
import SimplePeer, { SignalData, Instance } from 'simple-peer';

export default function VideoCallDiv() {
  enum ConnectionStatus {
      OFFERING,
      RECEIVING,
      CONNECTED,
  }
    
  const webSocketConnection = new WebSocket("ws://localhost:8080/videochat");
  
  const videoSelf = useRef<HTMLVideoElement | null>(null);
  const videoCaller = useRef<HTMLVideoElement | null>(null);
  const [connectionStatus, setConnectionStatus] = React.useState<ConnectionStatus | null>(null);
  const [offerSignal, setOfferSignal] = React.useState<SignalData>();
  const [simplePeer, setSimplePeer] = React.useState<Instance>();
  
  React.useEffect(() => {
    webSocketConnection.onmessage = (message: any) => {
      const payload = JSON.parse(message.data);
      if (payload?.type === "offer") {
        setOfferSignal(payload);
        setConnectionStatus(ConnectionStatus.RECEIVING);
      } else if (payload?.type === "answer") simplePeer?.signal(payload);
    };
  }, [simplePeer]);
  
  const sendOrAcceptInvitation = (isInitiator: boolean, offer?: SignalData) => {
    navigator.mediaDevices.getUserMedia({ video: true, audio: true }).then((mediaStream) => {
      const video = videoSelf.current;
      video!.srcObject = mediaStream;
      video!.play();

      const sp = new SimplePeer({
        trickle: false,
        initiator: isInitiator,
        stream: mediaStream,
      });

      if (isInitiator) setConnectionStatus(ConnectionStatus.OFFERING);
      else offer && sp.signal(offer);

      sp.on("signal", (data) => webSocketConnection.send(JSON.stringify(data)));
      sp.on("connect", () => setConnectionStatus(ConnectionStatus.CONNECTED));
      sp.on("stream", (stream) => {
        const video = videoCaller.current;
        video!.srcObject = stream;
        video!.play();
      });
      setSimplePeer(sp);
    });
  };
  
  return (
    <div className="web-rtc-page">
      {connectionStatus === null && <Button onClick={() => sendOrAcceptInvitation(true)}>JOIN CALL</Button>}
      {connectionStatus === ConnectionStatus.OFFERING && <div className="loader"/>}
      {connectionStatus === ConnectionStatus.RECEIVING && (
        <Button onClick={() => sendOrAcceptInvitation(false, offerSignal)}>ACCEPT PARTICIPANT</Button>
      )}
      <div className="video-container">
        <video ref={videoSelf} className="video-block" />
        <video ref={videoCaller} className="video-block" />
      </div>
    </div>
  );
}