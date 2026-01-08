package com.ruslangainutdinov.message.service.config;

import java.util.UUID;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class MyRuntimeHints implements RuntimeHintsRegistrar {

  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    hints.reflection().registerType(UUID[].class);
  }
}
