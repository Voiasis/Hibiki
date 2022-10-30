package net.vezio.tools.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.ByteBuffer;

//source (edited): https://github.com/sedmelluq/lavaplayer/tree/master/demo-jda

/**
 * This is a wrapper around AudioPlayer which makes it behave as an
 * AudioSendHandler for JDA. As JDA calls canProvide before every call to
 * provide20MsAudio(), we pull the frame in canProvide() and use the frame we
 * already pulled in provide20MsAudio().
 */
public class AudioPlayerSendHandler implements AudioSendHandler {
	private final AudioPlayer audioPlayer;
	private final ByteBuffer buffer;
	private final MutableAudioFrame frame;

	/**
	 * @param audioPlayer
	 *            Audio player to wrap.
	 */
	public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
		this.buffer = ByteBuffer.allocate(1024);
		this.frame = new MutableAudioFrame();
		this.frame.setBuffer(buffer);
	}

	@Override
	public boolean canProvide() {
		// returns true if audio was provided
		return audioPlayer.provide(frame);
	}

	@Override
	public ByteBuffer provide20MsAudio() {
		// flip to make it a read buffer
		return (ByteBuffer)buffer.flip();
	}

	@Override
	public boolean isOpus() {
		return true;
	}
}