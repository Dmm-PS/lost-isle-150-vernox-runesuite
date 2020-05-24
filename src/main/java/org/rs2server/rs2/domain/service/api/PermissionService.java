package org.rs2server.rs2.domain.service.api;

import org.rs2server.rs2.model.player.Player;

import javax.annotation.Nonnull;
import java.util.EnumSet;

/**
 * Represents a permission service.
 * @author Twelve
 */
public interface PermissionService {

    enum PlayerPermissions {
        PLAYER,
        HELPER,
        MODERATOR,
        EXTREME,
        ADMINISTRATOR,
        DONATOR,
        SPONSOR,
        COM,
        SUPER,
        IRON_MAN,
        ULTIMATE_IRON_MAN,
		HARDCORE_IRON_MAN,
        ;

        public static PlayerPermissions of(int forumRights) {
            switch (forumRights) {
                case 6:
                    return MODERATOR;
                case 8:
                    return EXTREME;
                case 7:
                    return SPONSOR;
                case 4:
                    return ADMINISTRATOR;
//                case 9:
//                	return PVP;
                case 10:
                    return HELPER;
                default:
                    return PLAYER;
            }
        }
    }

    PlayerPermissions getHighestPermission(@Nonnull Player player);

    void give(@Nonnull Player player, @Nonnull PlayerPermissions permission);

    void remove(@Nonnull Player player, @Nonnull PlayerPermissions permission);

    boolean is(@Nonnull Player player, @Nonnull PlayerPermissions permission);

    boolean isAny(@Nonnull Player player, @Nonnull PlayerPermissions... permissions);

    boolean isAny(@Nonnull Player player, @Nonnull EnumSet<PlayerPermissions> permissions);

    boolean isNot(@Nonnull Player player, @Nonnull PlayerPermissions permissions);

    boolean isNotAny(@Nonnull Player player, @Nonnull PlayerPermissions... permissions);

    boolean isNotAny(@Nonnull Player player, @Nonnull EnumSet<PlayerPermissions> permissions);
}
