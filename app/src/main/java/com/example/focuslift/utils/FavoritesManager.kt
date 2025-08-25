package com.example.focuslift.utils

import com.example.focuslift.models.FavoriteItem
import com.example.focuslift.models.FavoriteType
import java.util.*

object FavoritesManager {
    
    private val favorites = mutableListOf<FavoriteItem>()
    
    fun addFavorite(favorite: FavoriteItem) {
        if (!favorites.any { it.id == favorite.id }) {
            favorites.add(favorite)
        }
    }
    
    fun removeFavorite(favoriteId: String) {
        favorites.removeAll { it.id == favoriteId }
    }
    
    fun isFavorite(favoriteId: String): Boolean {
        return favorites.any { it.id == favoriteId }
    }
    
    fun getFavorites(): List<FavoriteItem> {
        return favorites.toList()
    }
    
    fun getFavoritesByType(type: FavoriteType?): List<FavoriteItem> {
        return if (type == null) {
            favorites.toList()
        } else {
            favorites.filter { it.type == type }
        }
    }
    
    fun searchFavorites(query: String): List<FavoriteItem> {
        val lowerQuery = query.lowercase()
        return favorites.filter { favorite ->
            favorite.title.lowercase().contains(lowerQuery) ||
            favorite.content.lowercase().contains(lowerQuery)
        }
    }
    
    fun getFavoritesCount(): Int = favorites.size
    
    fun getFavoritesCountByType(type: FavoriteType): Int {
        return favorites.count { it.type == type }
    }
}
