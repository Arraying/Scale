package de.arraying.scale

import de.arraying.scale.command.{Command, Context}
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.events.{Event, ReadyEvent}
import net.dv8tion.jda.core.hooks.EventListener
import net.dv8tion.jda.core.utils.JDALogger
import net.dv8tion.jda.core.{AccountType, JDABuilder}

import scala.util.{Failure, Success, Try}

/**
  * Copyright 2018 Arraying
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
object Scale extends EventListener {

  private val logger = JDALogger.getLog(this.getClass)
  private val prefix = "s/"

  /**
    * The entry point of the program.
    * @param args The runtime argument (arg1 should be the token)
    */
  def main(args: Array[String]): Unit = {
    if(args.length != 0)
      Try(
        new JDABuilder(AccountType.BOT)
          .setToken(args(0))
          .build()
          .awaitReady()
      ) match {
        case Success(x) => x.addEventListener(this)
        case Failure(x) => x.printStackTrace()
      }
  }

  /**
    * When the event is executed.
    * @param event The event.
    */
  override def onEvent(event: Event): Unit = {
    event match {
      case _: ReadyEvent => logger.info("Bot is ready!")
      case event: MessageReceivedEvent =>
        var message = event.getMessage.getContentRaw
        if(message.startsWith(prefix)) {
          message = message.substring(prefix.length)
          val args = message.split(" ")
          val name = args(0).toLowerCase
          Command.commands
            .filter(it => it.name == name || it.aliases.contains(name))
            .foreach(it => {
              it.execute(new Context(event.getAuthor, event.getChannel, args))
              logger.info(s"${event.getAuthor.getIdLong} executed the command $name in ${event.getChannel.getIdLong}.")
            })
        }
      case _ =>
    }
  }
}
