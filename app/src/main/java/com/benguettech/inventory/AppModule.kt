package com.benguettech.inventory

import com.benguettech.inventory.data.repository.AuthRepository
import com.benguettech.inventory.data.repository.InventoryRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseService(auth: FirebaseAuth, firestore: FirebaseFirestore): FirebaseService {
        return FirebaseService(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseService: FirebaseService): AuthRepository {
        return AuthRepository(firebaseService)
    }

    @Provides
    @Singleton
    fun provideInventoryRepository(firebaseService: FirebaseService): InventoryRepository {
        return InventoryRepository(firebaseService)
    }
}
