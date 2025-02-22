/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gnd.persistence.local;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Simple value store persisted locally on device. Unlike {@link LocalDataStore}, this class
 * provides a concrete implementation using the Android SDK, and therefore does not require a
 * database-specific implementation.
 */
@Singleton
public class LocalValueStore {
  private static final String ACTIVE_PROJECT_ID_KEY = "activeProjectId";

  private final SharedPreferences preferences;

  @Inject
  public LocalValueStore(SharedPreferences preferences) {
    this.preferences = preferences;
  }

  /** Returns the id of the last project successfully activated by the user, or null if not set. */
  @Nullable
  public String getLastActiveProjectId() {
    return preferences.getString(ACTIVE_PROJECT_ID_KEY, null);
  }

  /** Set the id of the last project successfully activated by the user. */
  public void setLastActiveProjectId(@NonNull String id) {
    preferences.edit().putString(ACTIVE_PROJECT_ID_KEY, id).commit();
  }

  /** Removes the last active project id in the local value store. */
  public void clearLastActiveProjectId() {
    preferences.edit().remove(ACTIVE_PROJECT_ID_KEY).commit();
  }
}
