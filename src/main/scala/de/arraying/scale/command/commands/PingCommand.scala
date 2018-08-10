package de.arraying.scale.command.commands

import de.arraying.scale.command.{Command, Context}

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
final class PingCommand extends Command("ping", Array("p")) {

  /**
    * Executes the command.
    * @param context The context.
    */
  override def execute(context: Context): Unit =
    context.channel.sendMessage(s"The ping is ${context.channel.getJDA.getPing}").queue()

}
