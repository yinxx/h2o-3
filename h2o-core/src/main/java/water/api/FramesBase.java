package water.api;

import water.Key;
import water.fvec.Frame;

// class FramesBase<H extends Handler<H,S>, S extends Schema<H,S>> extends Schema<H, S> {
abstract class FramesBase extends Schema<FramesHandler, FramesBase> {
  // Input fields
  @API(help="Key to inspect") // TODO: no validation yet, because right now fields are required if they have validation.
  Key key;

  // Output fields
  @API(help="Frames")
  FrameV1[] frames; // TODO: create interface or superclass (e.g., FrameBase) for FrameV1

  // Non-version-specific filling into the handler
  @Override protected FramesBase fillInto( FramesHandler h ) {
    h.key = this.key;

    if (null != frames) {
      h.frames = new Frame[frames.length];

      int i = 0;
      for (FrameV1 frame : this.frames) {
        h.frames[i++] = frame._fr;
      }
    }
    return this;
  }

  // Version&Schema-specific filling from the handler
  @Override protected FramesBase fillFrom( FramesHandler h ) {
    this.key = h.key;

    if (null != h.frames) {
      this.frames = new FrameV1[h.frames.length];

      int i = 0;
      for (Frame frame : h.frames) {
        this.frames[i++] = new FrameV1(frame);
      }
    }
    return this;
  }
}
