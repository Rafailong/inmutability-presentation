package me.rafaavila.immutability

import java.nio.file.Paths
import reftree.render.{Renderer, RenderingOptions}

trait MyRenderer {
  val renderer = Renderer(
    renderingOptions = RenderingOptions(density = 75),
    directory = Paths.get("slides", "overview")
  )
}
