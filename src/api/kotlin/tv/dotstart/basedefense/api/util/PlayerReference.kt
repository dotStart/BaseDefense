/*
 * Copyright 2019 Johannes Donath <johannesd@torchmind.com>
 * and other copyright owners as documented in the project's IP log.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tv.dotstart.basedefense.api.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import java.util.*

/**
 * Represents a serializable reference to an arbitrary player.
 *
 * The purpose of this object is to not only uniquely identify a player based on their profile UUID
 * but also store their last known display name in order to provide a human representation when
 * needed without having to perform costly network operations (which would count towards the
 * server's rate limit and cause conflicts with other mods).
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
data class PlayerReference(val id: UUID, var displayName: String) {

  companion object {
    const val playerIdTagName = "playerId"
    const val displayNameTagName = "displayName"
  }

  /**
   * Exposes a standardized NBT tag with which this reference may be stored persistently within
   * world data.
   *
   * This property is initialized lazily (e.g. is created once accessed for the first time) in order
   * to conserve memory where possible.
   */
  val nbt: NBTTagCompound by lazy {
    val tag = NBTTagCompound()
    tag.setUniqueId(playerIdTagName, this.id)
    tag.setString(displayNameTagName, this.displayName)
    tag
  }

  constructor(player: EntityPlayer) : this(player.persistentID, player.displayNameString)

  constructor(tag: NBTTagCompound) : this(
      tag.getUniqueId(playerIdTagName) ?: throw IllegalArgumentException(
          "Missing structure property: playerId"),
      tag.getString(displayNameTagName) ?: throw IllegalArgumentException(
          "Missing structure property: displayName")
  )
}
